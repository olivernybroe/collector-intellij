<?php

$array = [
    'one',
    'two',
    'tree'
];

$expectations = [
    'one'
];

function myMethod(string $value, string $secondValue) {}

collect($array)->each(function ($string) use ($expectations) {
    foreach ($expectations as $expectation) {
        myMethod($string, $expectation);
    }
});