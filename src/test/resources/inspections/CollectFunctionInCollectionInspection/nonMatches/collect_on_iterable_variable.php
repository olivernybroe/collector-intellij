<?php

class IterableVariable {

}

/** @return IterableVariable[] */
function getVariable(): iterable
{
    return [];
}

collect(getVariable());
