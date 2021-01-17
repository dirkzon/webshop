describe("Logging in", function () {

    context("Successful create account sequence", function () {
        beforeEach(function () {
            cy.visit("/account/new");
            cy.clearCookie("access_token")
        });

        it("Successfully create account for customer", () => {
            cy.server();

            cy.get("[data-cy=email]").type("customer@mail.com");
            cy.get("[data-cy=username]").type("testCustomer");
            cy.get("[data-cy=password]").type("testPassword");
            cy.get("[data-cy=country]").type("Netherlands");
            cy.get("[data-cy=streetName]").type("Timmerstraat");
            cy.get("[data-cy=houseNumber]").type("45");

            cy.get("[data-cy=createAccount]").click()

            cy.wait(1000);

            cy.get("[data-cy=username]").type("testCustomer");
            cy.get("[data-cy=password]").type("testPassword");

            cy.get("[data-cy=loginButton]").click();
        });

        it("Successfully create account for retailer", () => {
            cy.server();

            cy.get("[data-cy=email]").type("retailer@mail.com");
            cy.get("[data-cy=username]").type("testRetailer");
            cy.get("[data-cy=password]").type("testPassword");
            cy.get("[data-cy=accountType]").type('{enter}{uparrow}{enter}', {force: true})

            cy.get("[data-cy=createAccount]").click()

            cy.wait(1000);

            cy.get("[data-cy=username]").type("retailer@mail.com");
            cy.get("[data-cy=password]").type("testPassword");

            cy.get("[data-cy=loginButton]").click();
        });
    });
})
