<?php

$array = [
    'one',
    'two',
    'tree'
];


$this->name = 'one';

collect($array)->each(function ($item) {
    $this->name = $item;
});
