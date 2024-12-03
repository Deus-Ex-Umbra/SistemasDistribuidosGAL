<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    public function up(): void
    {
        Schema::create('facturas', function (Blueprint $table) {
            $table->id();
            $table->string('ci', 16);
            $table->date('fecha');
            $table->decimal('total', 8, 2);
            $table->boolean('pagado');
        });
    }

    public function down(): void
    {
        Schema::dropIfExists('facturas');
    }
};
