<?php

<weak_warning descr="'where()->isNotEmpty()' used instead of 'contains()'">collect([
    ['name' => 'Monkey'],
    ['name' => 'Cat'],
])->where('name', 'Monkey')->isNotEmpty()</weak_warning>;
