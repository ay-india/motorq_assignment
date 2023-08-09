# motorq_assignment
motorq_assignment
Absolutely, let's present the instructions in a distinct style to make them look fresh and original:

## Cryptocurrency Information Backend App

Unlock the world of cryptocurrency data with this backend application, which retrieves and updates information using AirTable and CoinGecko API.

### Prerequisites

Before embarking on your journey, ensure you have the following tools and accounts ready:

1. A Java Development Kit (JDK) installed on your machine.
2. Maven, the reliable build tool, up and running.
3. An AirTable account for stashing cryptocurrency data.
4. (Optional) A CoinGecko API account to level up your cryptocurrency knowledge.

### Get Started



**1. Configure AirTable**

- Join the AirTable club (if you're not a member already).
- Create a "Cryptocurrency" table to hold your treasure.
- Remember your AirTable API key and base ID for future adventures.


**2. Update Application Configuration**

- Open `src/main/resources/application.properties`.
- Get your actual AirTable API key.

### Unleash the Magic

**1. Create AirTable Data Model**

- Craft a class named `AirTableCoin` to encapsulate your cryptocurrency data.

**2. Forge the AirTable Service**

- Craft your service class named `AirTableService` to wield your CRUD operations with the AirTable API.

**3. Forge a Cache to Remember**

- Set up a powerful cache using Spring's caching abstraction.
- Shape `CoinCacheService` to guard and access your frequently visited data.

**4. Embrace Background Magic**

- Harness the power of `BackgroundJobScheduler` to periodically update coin details from CoinGecko to AirTable.

**5. Forge APIs**

- Craft your own REST API endpoints within `CoinController` to summon coin information from AirTable.


### Explore the Realms

- **GET** `/coins`: Retrieve coin knowledge from AirTable.
- **GET** `/coins/price/{coinId}`: Tap into the cache and fetch the current price of a coin.


#

