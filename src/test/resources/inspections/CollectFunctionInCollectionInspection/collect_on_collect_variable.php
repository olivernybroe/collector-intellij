<?php

$variable = collect([1,2,3]);

$sum = <warning descr="Recursive call on collection">collect($variable)</warning>->sum();
