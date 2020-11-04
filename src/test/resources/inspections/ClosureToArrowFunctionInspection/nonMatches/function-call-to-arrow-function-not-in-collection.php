<?php

$array = [
    'one',
    'two',
    'tree'
];

otherClass($array)->each(function ($item) {
    doAction($item);
});

collect($array)->map()->each(fn($item) => doAction($item));
