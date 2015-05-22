Scenario: User searches for a single step
 
Given user is on Home page
When user sets parameters mass 80 and height 190
Then I give 22.16
When user opens contact link
Then contact page is shown
When user not set parameters 
Then get warning