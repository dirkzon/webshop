describe("Editing account", function () {
    context("customer account", function () {
        beforeEach(function () {
            cy.clearCookie("access_token")
            cy.setCookie("access_token", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoZW5rIiwianRpIjoiMSIsIlJvbGUiOiJDdXN0b21lciIsImlhdCI6MTYwOTQwODUwNn0.-FiQD0LTRPqbywjC0Qhp9hbH9S_zkPhkYHvtunER0nA")
            cy.visit("/customer/me");
            cy.wait(2000)
        });

        it("Successfully update customer account", () => {
            cy.get("[data-cy=username]").clear().type("updated_name");
            cy.get("[data-cy=email]").clear().type("updated_email");
            cy.get("[data-cy=password]").clear().type("updated_password");
            cy.get("[data-cy=address]").clear().type("updated_address");
            cy.get("[data-cy=streetName]").clear().type("updated_street_name");
            cy.get("[data-cy=houseNumber]").clear().type("33");
            cy.get("[data-cy=update]").click();
            cy.get("[data-cy=username]").should('have.value', 'updated_name')
            cy.get("[data-cy=email]").should('have.value', 'updated_email')
            cy.get("[data-cy=password]").should('have.value', 'updated_password')
            cy.get("[data-cy=address]").should('have.value', 'updated_address')
            cy.get("[data-cy=streetName]").should('have.value', 'updated_street_name')
            cy.get("[data-cy=houseNumber]").should('have.value', '33')
        });

        it("Successfully delete customer account", () => {
            cy.get("[data-cy=remove]").click();
            cy.get("[data-cy=confirmRemove]").click();
            cy.wait(1000);
            cy.get("[data-cy=username]").type("updated_name");
            cy.get("[data-cy=password]").type("updated_password");

            cy.get("[data-cy=loginButton]").click();

            cy.wait(2000);

            cy.getCookie("access_token").should('not.exist');
        });

    });

    context("retailer account", function () {
        beforeEach(function () {
            cy.clearCookie("access_token")
            cy.setCookie("access_token", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huIiwianRpIjoiMSIsIlJvbGUiOiJSZXRhaWxlciIsImlhdCI6MTYwOTQyMzMwOH0.qYkCmfEweoAbHxO5HiKX2amWWgIQjWTiE_ffKepUeVw")
            cy.visit("/retailer/me");
            cy.wait(2000)
        });

        it("Successfully update retailer account", () => {
            cy.get("[data-cy=username]").clear().type("updated_name");
            cy.get("[data-cy=email]").clear().type("updated_email");
            cy.get("[data-cy=password]").clear().type("updated_password");
            cy.get("[data-cy=update]").click();
            cy.get("[data-cy=username]").should('have.value', 'updated_name')
            cy.get("[data-cy=email]").should('have.value', 'updated_email')
            cy.get("[data-cy=password]").should('have.value', 'updated_password')
        });

        it("Successfully delete retailer account", () => {
            cy.get("[data-cy=remove]").click();
            cy.get("[data-cy=confirmRemove]").click();
            cy.wait(1000);
            cy.get("[data-cy=username]").type("updated_name");
            cy.get("[data-cy=password]").type("updated_password");

            cy.get("[data-cy=loginButton]").click();

            cy.wait(2000);

            cy.getCookie("access_token").should('not.exist');
        });
    });
})
