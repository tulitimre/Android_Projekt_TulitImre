<?php

namespace App\Http\Controllers;


use App\Http\Resources\EtelResource;
use App\Models\Etel;

//use http\Env\Request;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class EtteremApiController extends Controller
{
    public function ettermek()
    {
        return DB::table('ettermek')->select('nev', 'ertekeles', 'ertekeles_db', 'hazhozszallit', 'szal_ar', 'minimum_rendeles', 'cim', 'telefonszam')->get();
    }

    public function etelek($tipus)
    {
        return DB::table('etelek')
            ->join('eteltipusok', 'eteltipusok.id', '=', 'etelek.eteltipusok_id')
            ->join('ettermek', 'ettermek.id', '=', 'etelek.ettermek_id')
            ->where('eteltipusok.nev', '=', $tipus)
            ->select('etelek.nev AS nev', 'eteltipusok.nev AS típus', 'etelek.ar', 'etelek.mennyiseg', 'ettermek.nev AS etterem')
            ->get();
    }

    public function etelMindenEtteremben($etelNeve)
    {
        if (!(empty(preg_replace('/\s+/', '', $etelNeve)))) {
            $etelExists = DB::table('etelek')->select('id')->where('etelek.nev', '=', $etelNeve)->first();
            if (!(is_null($etelExists))) {
                $isComparable = DB::table('osszehasonlithato')->where('osszehasonlithato.etelek_id', '=', $etelExists->id)->first();
            }
            if (!is_null($etelExists) && !is_null($isComparable)) {
                $theFirstId = DB::table('etelek')->select('id')->where('etelek.nev', $etelNeve)->first();
                $theFirstIdPrice = DB::table('osszehasonlithato')->select('ar_per_menny')->where('etelek_id', $theFirstId->id)->first();
                $idOfTheBest = $theFirstId->id;
                $bestPrice = $theFirstIdPrice->ar_per_menny;
                $haveGreater = false;
                while ($haveGreater === false) {
                    $possibleBestPrice = DB::table('osszehasonlithato')
                        ->select('etelek_id1', 'ar_per_menny1')
                        ->where("etelek_id", "=", $idOfTheBest)
                        ->where("ar_per_menny1", "<", $bestPrice)
                        ->first();
                    if (!(is_null($possibleBestPrice))) {
                        if ($possibleBestPrice->ar_per_menny1 < $bestPrice) {
                            $idOfTheBest = $possibleBestPrice->etelek_id1;
                            $bestPrice = $possibleBestPrice->ar_per_menny1;
                        } else {
                            $haveGreater = true;
                        }
                    } else {
                        $haveGreater = true;
                    }
                }
                return DB::table('etelek')
                    ->join('osszehasonlithato', 'osszehasonlithato.etelek_id', 'etelek.id')
                    ->join('eteltipusok', 'eteltipusok.id', '=', 'etelek.eteltipusok_id')
                    ->join('ettermek', 'ettermek.id', '=', 'etelek.ettermek_id')
                    ->where('osszehasonlithato.etelek_id1', $idOfTheBest)
                    ->orWhere('osszehasonlithato.etelek_id', '=', $idOfTheBest)
                    ->select('etelek.nev AS nev', 'eteltipusok.nev AS típus', 'etelek.ar', 'etelek.mennyiseg', 'ettermek.nev AS etterem')
                    ->distinct()
                    ->addSelect(DB::raw("(etelek.id = $idOfTheBest) AS legjobb_ar_mennyiseg"))
                    ->get();

            } else if (!is_null($etelExists) && is_null($isComparable)) {
                return DB::table('etelek')
                    ->join('eteltipusok', 'eteltipusok.id', '=', 'etelek.eteltipusok_id')
                    ->join('ettermek', 'ettermek.id', '=', 'etelek.ettermek_id')
                    ->where('etelek.nev', '=', $etelNeve)
                    ->select('etelek.nev AS nev', 'eteltipusok.nev AS típus', 'etelek.ar', 'etelek.mennyiseg', 'ettermek.nev AS etterem')
                    ->get();
            } else {
                return response()->json([
                    'eredmény' => false,
                    'üzenet' => 'Nincs ilyen étel egy étteremben sem!'
                ]);
            }
        } else {
            return response()->json([
                'eredmény' => false,
                'üzenet' => 'Adja meg a keresett étel nevét!'
            ]);
        }

    }

    public function eteltipusEtteremben($etteremNev, $etelTipus)
    {
        $exists = DB::table('etelek')
            ->join('eteltipusok', 'eteltipusok.id', '=', 'etelek.eteltipusok_id')
            ->join('ettermek', 'ettermek.id', '=', 'etelek.ettermek_id')
            ->where('ettermek.nev', '=', $etteremNev)
            ->where('eteltipusok.nev', '=', $etelTipus)
            ->select('etelek.nev AS nev', 'eteltipusok.nev AS típus', 'etelek.ar', 'etelek.mennyiseg', 'ettermek.nev AS etterem')
            ->get();

        if (!($exists->count() == 0)) {
            return $exists;
        } else {
            return response()->json([
                'eredmény' => false,
                'üzenet' => 'Nincs ilyen típusú étel ebben az étteremben!'
            ]);
        }
    }

    public function ujEtel()
    {

        $etteremId = DB::table('ettermek')
            ->select('ettermek.id')
            ->where('ettermek.nev', request('etterem'))
            ->first();

        $tipusId = DB::table('eteltipusok')
            ->select('eteltipusok.id')
            ->where('eteltipusok.nev', request('eteltipus'))
            ->first();

        if (!(empty(request('etelnev')))) {
            $existsFood = DB::table('etelek')
                ->where('etelek.nev', request('etelnev'))
                ->where('etelek.eteltipusok_id', $tipusId->id)
                ->where('etelek.ettermek_id', $etteremId->id)
                ->where('etelek.ar', request('ar'))
                ->where('etelek.mennyiseg', request('mennyiseg'))
                ->get();
            if ($existsFood->count() == 0) {
                $priceOfTheNewFood = (int)request()->get('ar');
                if ($priceOfTheNewFood <= 0) {
                    return response()->json([
                        'eredmény' => false,
                        'üzenet' => 'Az ár nagyobb kell legyen, mint 0!'
                    ]);
                }
                $quantityOfTheNewFood = (int)request()->get('mennyiseg');
                if ($quantityOfTheNewFood <= 0) {
                    return response()->json([
                        'eredmény' => false,
                        'üzenet' => 'Az mennyiseg nagyobb kell legyen, mint 0!'
                    ]);
                }
                $eteletipusIDString = (string)$tipusId->id;
                $createdId = DB::table('etelek')
                    ->where('etelek.eteltipusok_id', $eteletipusIDString)
                    ->max('id');
                $existsId = DB::table('etelek')->where('etelek.id', $createdId + 1);
                if ($existsId->count() >= 1) {
                    $createdId = (DB::table('etelek')->max('id')) + 1;
                } else {
                    $createdId++;
                }
                DB::table('etelek')->insert([
                    'id' => $createdId,
                    'nev' => request('etelnev'),
                    'eteltipusok_id' => $tipusId->id,
                    'ettermek_id' => $etteremId->id,
                    'ar' => request('ar'),
                    'mennyiseg' => request('mennyiseg')
                ]);
                return response()->json([
                    'etterem' => request('etterem'),
                    'tipus' => request('eteltipus'),
                    'nev' => request('etelnev'),
                    'ar' => request('ar'),
                    'mennyiseg' => request('mennyiseg')
                ]);
            } else {
                return response()->json([
                    'eredmény' => false,
                    'üzenet' => 'Ez az étel már létezik ebben az étteremben!'
                ]);
            }
        } else {
            return response()->json([
                'eredmény' => false,
                'üzenet' => 'Adja meg az étel nevét!'
            ]);
        }

    }

    public function destroy(Etel $etel)
    {
        $etel->delete();
        return new EtelResource($etel);
    }

    public function update(Request $request, Etel $etel)
    {
        $etel->update($request->all());
        return new EtelResource($etel);
    }




}
