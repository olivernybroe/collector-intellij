<?php

if(rand(1, 10)<5){
    $bar = [];
}else{
    $bar = new \Illuminate\Support\Collection();
}
collect($bar);