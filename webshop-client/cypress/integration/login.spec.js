describe("Logging in", function () {

    context("Successful log in sequence", function () {
        beforeEach(function () {
            cy.visit("/login");
            cy.clearCookie("access_token")
        });

        it("Successful login for customer", () =>{
            cy.server();

            cy.intercept("/").as("homePage")

            cy.get("[data-cy=username]").type("henk");
            cy.get("[data-cy=password]").type("1234");

            cy.get("[data-cy=loginButton]").click();
        })

        it("Successful login for retailer", () =>{
            cy.server();

            cy.get("[data-cy=username]").type("john");
            cy.get("[data-cy=password]").type("abcd");

            cy.get("[data-cy=loginButton]").click();
        })

        it("Unsuccessful login", () =>{
            cy.server();

            cy.get("[data-cy=username]").type("invalid");
            cy.get("[data-cy=password]").type("LetMeIn");

            cy.get("[data-cy=loginButton]").click();
        })
    })
})


