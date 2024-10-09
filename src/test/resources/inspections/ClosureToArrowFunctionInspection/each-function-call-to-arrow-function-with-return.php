<?php

$array = [
    'one',
    'two',
    'tree'
];

collect($array)->each(<caret>function ($item) {
    return doAction($item);
});
