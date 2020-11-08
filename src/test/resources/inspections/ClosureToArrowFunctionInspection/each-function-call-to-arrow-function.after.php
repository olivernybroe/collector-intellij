<?php

$array = [
    'one',
    'two',
    'tree'
];

collect($array)->each(fn($item) => doAction($item));
