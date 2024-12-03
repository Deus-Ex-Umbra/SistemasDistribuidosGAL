<?php

namespace Database\Factories;

use Illuminate\Database\Eloquent\Factories\Factory;

class FacturaFactory extends Factory
{

    public function definition(): array
    {
        return [
            'ci' => $this->faker->randomNumber(8),
            'fecha' => $this->faker->date(),
            'total' => $this->faker->randomFloat(2, 0, 9999),
            'pagado' => 0
        ];
    }
}
