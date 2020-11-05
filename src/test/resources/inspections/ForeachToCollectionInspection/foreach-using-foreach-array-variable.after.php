<?php

$array = [
    'one',
    'two',
    'tree'
];

collect($array)->each(function ($item) use ($array) {
    blah($array, $item);
});
