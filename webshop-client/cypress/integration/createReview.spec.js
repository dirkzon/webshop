describe("Create review", function () {

    context("Successfully create a review on a product", function () {
        beforeEach(function () {
            cy.server();
            cy.clearCookie("access_token")
            cy.setCookie("access_token", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoZW5rIiwianRpIjoiMSIsIlJvbGUiOiJDdXN0b21lciIsImlhdCI6MTYwOTQwODUwNn0.-FiQD0LTRPqbywjC0Qhp9hbH9S_zkPhkYHvtunER0nA")
            cy.clearCookie("scope")
            cy.setCookie("scope", "Customer")
            cy.visit("/product/2");
        });

        it("Successfully create a review on a product", () => {
            cy.get("[data-cy=reviewBody]").type("This is a test review");
            cy.get("[data-cy=postReview]").click()
            cy.wait(1000)
            cy.contains("This is a test review")
        });

        it("Cannot create a second review on same product", () => {
            cy.get("[data-cy=postReview]").should('be.disabled')
        });
    });
})
