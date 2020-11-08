<?php

$array = [
    'one',
    'two',
    'tree'
];

collect($array)->each(function ($item) {
    doAction($item);
});
