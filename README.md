# mathathon17-server
Backend server created for Mathathon, a hackathon for math students. Challenge was to computationally reproduce a given image with 30 transparent colored triangles. Scoring is the sum of color differences over all the pixels, with lower score being better. Service receives a JSON of a set of triangles (edge coordinates, RBG values and opacity), constructs the image and then calculates the difference.

## Functionalities
1) Upload a solution with your team ID
2) List best solutions for each team
3) Show pictures of each best solution

## Other
Currently only supports one target image and the image can't be changed to another one without restarting the system. This should be finished if project is ever re-used.

Buzzwords: Java, Spring Boot, Maven
