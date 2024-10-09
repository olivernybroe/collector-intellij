<?php

$array = [
    'one',
    'two',
    'tree'
];

collect($array)->each(<caret>function ($item) {
    doAction($item);
});
