<?php

$array = [
    'one',
    'two',
    'tree'
];

collect($array)->map(function ($string) {
    if ($string) {
        return $string;
    }
});