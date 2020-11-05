<?php

$array = [
    'one',
    'two',
    'tree'
];

$name = 'one';

collect($array)->each(function ($item) use ($name) {
    if ($item === $name) {
        echo "found " . $name;
    }
});
