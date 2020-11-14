<?php

<weak_warning descr="'where()->first()' used instead of 'firstWhere()'">collect([
    ['name' => 'Monkey'],
    ['name' => 'Cat'],
])->where('name', 'Monkey')->first()</weak_warning>;
