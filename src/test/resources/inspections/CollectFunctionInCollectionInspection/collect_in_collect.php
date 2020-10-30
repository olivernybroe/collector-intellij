<?php

$sum =  <warning descr="Recursive call on collection">collect(collect([1,2,3]))</warning>->sum();
