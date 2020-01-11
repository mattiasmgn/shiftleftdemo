Feature: Fruit shopping
  Our online shop can show prices of fruits
  Scenario: lookup price for a particular fruit
    When GET request for "2" "apple" to the price method is executed
    Then response from fruit price method should be correct
    """
     {"fruit":"apple","units":2,"price":10.383583424437363,"currency":"NOK"}
    """
































# (if you cannot run this by right-click and run with the error
#  Exception in thread "main" java.lang.NoClassDefFoundError: org/springframework/transaction/TransactionDefinition
#  make sure glue-settings is com.foreach.cuke se.freefarm.fruit.rest )