describe("Editing product", function () {
    context("product", function () {
        beforeEach(function () {
            cy.server();
            cy.clearCookie("access_token")
            cy.visit("/login");
            cy.get("[data-cy=username]").type("john");
            cy.get("[data-cy=password]").type("abcd");

            cy.get("[data-cy=loginButton]").click();
            cy.wait(2000)
            });

        it("Successfully update product", () => {
            cy.visit("/product/2/edit");
            cy.wait(2000)
            cy.get("[data-cy=name]").clear().type("updated_name");
            cy.get("[data-cy=description]").clear().type("updated_description");
            cy.get("[data-cy=price]").clear().type("30");
            cy.get("[data-cy=image]").clear().type("http://photos.gograph.com/thumbs/CSP/CSP994/k16154725.jpg");
            cy.get("[data-cy=update]").click();
            cy.wait(2000)
            cy.contains("updated_name")
            cy.contains("updated_description")
            cy.contains("$30.00")
            console.log(cy.get("[data-cy=image]"))
        });

        it("Successfully remove product", () => {
            cy.visit("/product/2/edit");
            cy.wait(2000)
            cy.get("[data-cy=remove]").click();
            cy.get("[data-cy=confirmRemove]").click();
            cy.visit("/product/2");
            cy.wait(1000)
            cy.get('[data-cy=name]').should('not.exist');
            cy.get('[data-cy=description]').should('not.exist');
            cy.get('[data-cy=price]').should('not.exist');
        });
    });
})
