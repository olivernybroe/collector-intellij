<?php

$array = [
    'one',
    'two',
    'tree'
];

$name = 'one';

collect($array)->each(function ($item) use (&$name) {
    doAction($item, $name);
});
