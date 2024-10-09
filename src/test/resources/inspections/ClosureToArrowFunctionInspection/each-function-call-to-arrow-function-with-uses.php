<?php

$array = [
    'one',
    'two',
    'tree'
];

$name = 'one';

collect($array)->each(<caret>function ($item) use ($name) {
    doAction($item, $name);
});
