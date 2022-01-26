<?php

use App\Http\Controllers\EtteremApiController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});

Route::get('/ettermek', [EtteremApiController::class, 'ettermek']);
Route::get('/etelek/{eteltipus}', [EtteremApiController::class, 'etelek']);
Route::get('/etel/{etelneve}', [EtteremApiController::class, 'etelMindenEtteremben']);
Route::get('/ettermek/{etteremnev}/{eteltipusnev}', [EtteremApiController::class, 'eteltipusEtteremben']);
Route::post('/ujetel', [EtteremApiController::class, 'ujEtel']);
Route::delete('/etel/{etel}', [EtteremApiController::class, 'destroy']);
Route::put('/etel/{etel}', [EtteremApiController::class, 'update']);

