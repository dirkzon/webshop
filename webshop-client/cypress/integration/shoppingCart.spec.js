describe("Shopping cart tests", function () {

    context("Successfully add product to cart", function () {
        beforeEach(function () {
            cy.clearCookie("access_token")
            cy.setCookie("access_token", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoZW5rIiwianRpIjoiMSIsIlJvbGUiOiJDdXN0b21lciIsImlhdCI6MTYwOTQwODUwNn0.-FiQD0LTRPqbywjC0Qhp9hbH9S_zkPhkYHvtunER0nA")
            cy.clearCookie("scope")
            cy.setCookie("scope", "Customer")
        });

        it("Successfully add product with id 2 to cart", () => {
            cy.visit("/product/2");
            cy.get("[data-cy=addToCart]").click()
            cy.get("[data-cy=addToCart]").should('be.disabled')
        });

        it("Successfully add product with id 6 to cart", () => {
            cy.visit("/product/6");
            cy.get("[data-cy=addToCart]").click()
            cy.get("[data-cy=addToCart]").should('be.disabled')
        });

        it("Successfully view cart", () => {
            cy.setCookie("cart", "[2,6]");
            cy.visit("/cart");
            cy.wait(1000)
            cy.contains("Total: $823.60")
        });

        it("Successfully remove product from cart", () => {
            cy.setCookie("cart", "[2,6]");
            cy.visit("/cart");
            cy.wait(1000)
            cy.get("[data-cy=remove]").eq(0).click();
            cy.contains("Total: $623.60")
        });
    })
})
