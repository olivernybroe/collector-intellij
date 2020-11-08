<?php

$array = [
    'one',
    'two',
    'tree'
];

$name = 'one';

collect($array)->each(fn($item) => doAction($item, $name));
