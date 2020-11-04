<?php

$array = [
    'one',
    'two',
    'tree'
];


$this->name = 'one';

<caret>foreach ($array as $item) {
    $this->name = $item;
}
