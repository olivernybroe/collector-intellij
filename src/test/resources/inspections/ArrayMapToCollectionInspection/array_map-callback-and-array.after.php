<?php

$b = collect([1, 2, 3, 4, 5])->map(function ($n) {
    return ($n * $n * $n);
})->all();
