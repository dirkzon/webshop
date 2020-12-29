describe("Create review", function () {


    context("Successfully create a review on a product", function () {
        beforeEach(function () {
            cy.visit("/login");
            cy.clearCookie("access_token")
            cy.server();

            cy.get("[data-cy=username]").type("henk");
            cy.get("[data-cy=password]").type("1234");

            cy.get("[data-cy=loginButton]").click();

            cy.wait(1000);
            cy.visit("/product/2");
        });

        it("Successful login for customer", () => {
            cy.server();
            cy.get("[data-cy=reviewBody]").type("This is a test review");
            cy.get("[data-cy=postReview]").click()
        });
    });
})
