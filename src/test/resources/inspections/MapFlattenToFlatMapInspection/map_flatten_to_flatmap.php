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

<weak_warning descr="'map()->flatten()' used instead of 'flatmap()'">collect($products)->map(function($product) {
    return $product['variants'];
})->flatten(1)</weak_warning>;
