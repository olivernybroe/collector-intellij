<?php

collect([
    ['name' => 'Monkey'],
    ['name' => 'Cat'],
])->where('name', 'Monkey')->first(function ($value) {
    return strlen($value) > 3;
});
