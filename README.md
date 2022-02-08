# The Ask:
Write an Android app which allows a user to see the list of popular items for sale on Depop.

The list should be downloaded from the following JSON endpoint:
https://api.garage.me/api/v1/products/popular/?offset_id=

The app should load this automatically when launched and display the results to the user as a list of one item per line. Each item
should at least show a short description and the user’s id.

The user can select any of these items to see all the information related to that item. The
item detail page should at least show all the following information:
the user’s id the item’s full description all the
pictures available for this item (up to 4)

Each image should be downloaded from the correct url provided in the JSON. We provide different sizes for
the same picture, each of which is served by a specific url. Example:
"P1": { "height": 640, "width": 640, "url": “ ”
http://pictures.depop.com/b0/2513227/233594315_vQnPrfpKJX/P1.jpg }

The app should try to download the best
picture url based on the size of the picture to display on screen.
