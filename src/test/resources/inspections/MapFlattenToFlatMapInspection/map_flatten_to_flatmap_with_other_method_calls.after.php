<?php

$products = [
    [
        'name' => 'Desk lamp',
        'variants' => [
            ['title' => 'Blue', 'price' => 20.33],
            ['title' => 'Green', 'price' => 23.33],
        ]
    ],
    [
        'name' => 'Desk',
        'variants' => [
            ['title' => 'Black', 'price' => 199.95],
        ]
    ],
];

collect($products)->filter(function ($product) {
    return $product['name'] === 'Desk';
})->flatMap(function($product) {
    return $product['variants'];
})->pluck('price')->sum();
