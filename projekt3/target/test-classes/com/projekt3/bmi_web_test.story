Scenario: User searches for a single step
 
Given user is on Home page
When user sets parameters mass 80 and height 190
When user opens contact link
Then contact page is shown
When user opens rules link
Then rules page is shown