describe("Create review", function () {

    context("Successfully create a review on a product", function () {
        beforeEach(function () {
            cy.server();
            cy.visit("/login");
            cy.clearCookie("access_token")
            cy.get("[data-cy=username]").type("henk");
            cy.get("[data-cy=password]").type("1234");

            cy.get("[data-cy=loginButton]").click();
            cy.visit("/product/2");
            cy.wait(2000);
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
