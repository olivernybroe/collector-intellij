<?php

$array = [
    'one',
    'two',
    'tree'
];

collect($array)->each(function ($item) {
    echo "found {$item}";
});
