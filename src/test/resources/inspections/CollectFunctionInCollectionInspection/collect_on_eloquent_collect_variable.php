<?php

$variable = new \Illuminate\Database\Eloquent\Collection([]);

$sum = <warning descr="Recursive call on collection">collect($variable)</warning>->sum();
