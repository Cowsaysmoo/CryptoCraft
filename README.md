# CryptoCraft (Version a.1.2)
### A Cryptocurrency trading plugin for Bukkit Minecraft servers!

This plugin implements the block.io API for in-game cryptocurrency trading.

In order to use this plugin, the server administrator must set up an account on block.io and include the provided API key(s), as well as a custom "Secret PIN", in the generated config file "config.yaml".

It is also recommended to change the default donate address to one owned by the server administrator.

For troubleshooting purposes, block.io has 3 different worthless test currencies, which can be included with seperate API keys provided by block.io.

## Commands
    tip:
      Tip any player coins, defaults to anonymous tip.
      usage: /tip <doge,btc,ltc> <player> <amount> <anon y,n>
    withdraw:
      Withdraw coins to an address.
      usage: /withdraw <doge,btc,ltc> <address> <amount>
    donate:
      Donate to the server.
      usage: /donate <doge,btc,ltc> <amount>
    balance:
      See how many coins you have.
      usage: /balance <doge,btc,ltc>
    deposit:
      Display your deposit address.
      usage: /deposit <doge,btc,ltc>
    wallet:
      Create wallet.
      usage: /wallet <doge,btc,ltc>
    shift (WIP):
      Trade coins.
      usage: /shift <amount> <from-coin> <to-coin>
    QR:
      Show wallet QR.
      usage: /QR <coin>
