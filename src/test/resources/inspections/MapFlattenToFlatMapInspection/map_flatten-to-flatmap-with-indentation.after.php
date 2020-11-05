<?php

collect($shopifyOrder->fulfillments)
    ->flatMap(
        fn (ShopifyFulfillment $fulfillment) => $this->mapLineItems($order, $shopifyOrder, $fulfillment)
    );
