describe("Shopping cart tests", function () {

    context("Successfully add product to cart", function () {
        beforeEach(function () {
            cy.clearCookie("access_token")
            cy.server();
            cy.clearCookie("access_token")
            cy.visit("/login");
            cy.get("[data-cy=username]").type("henk");
            cy.get("[data-cy=password]").type("1234");

            cy.get("[data-cy=loginButton]").click();
            cy.wait(2000)
        });

        it("Successfully add product with id 2 to cart", () => {
            cy.visit("/product/4");
            cy.get("[data-cy=addToCart]").click()
            cy.get("[data-cy=addToCart]").should('be.disabled')
        });

        it("Successfully add product with id 6 to cart", () => {
            cy.visit("/product/6");
            cy.get("[data-cy=addToCart]").click()
            cy.get("[data-cy=addToCart]").should('be.disabled')
        });
    })
})
