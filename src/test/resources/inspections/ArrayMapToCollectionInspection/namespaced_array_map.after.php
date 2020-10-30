<?php

namespace MyAwesomeName\Good;

$a = [1, 2, 3, 4, 5];
$b = collect($a)->map(function ($n) {
    return ($n * $n * $n);
})->all();
