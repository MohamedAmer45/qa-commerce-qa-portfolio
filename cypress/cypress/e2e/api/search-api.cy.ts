describe("Search API", () => {
  it("searches by product name", () => {
    cy.request("/api/search?q=keyboard").then((response) => {
      expect(response.status).to.equal(200);

      expect(response.body.query).to.equal("keyboard");

      expect(response.body.count).to.equal(1);

      expect(response.body.data[0].name).to.equal(
        "Pulse 75 Mechanical Keyboard",
      );
    });
  });

  it("searches by brand", () => {
    cy.request("/api/search?q=Northstar").then((response) => {
      expect(response.status).to.equal(200);

      expect(response.body.data).to.have.length.greaterThan(0);

      expect(response.body.data[0].brand).to.equal("Northstar");
    });
  });

  it("searches by category", () => {
    cy.request("/api/search?q=Accessories").then((response) => {
      expect(response.status).to.equal(200);

      expect(response.body.count).to.equal(3);

      response.body.data.forEach((product: { category: string }) => {
        expect(product.category).to.equal("Accessories");
      });
    });
  });

  it("search is case insensitive", () => {
    cy.request("/api/search?q=KEYBOARD").then((response) => {
      expect(response.status).to.equal(200);

      expect(response.body.count).to.equal(1);

      expect(response.body.data[0].id).to.equal(2);
    });
  });

  it("trims search whitespace", () => {
    cy.request("/api/search?q=%20keyboard%20").then((response) => {
      expect(response.status).to.equal(200);

      expect(response.body.count).to.equal(1);

      expect(response.body.data[0].id).to.equal(2);
    });
  });

  it("returns empty results when nothing matches", () => {
    cy.request("/api/search?q=zzzz-no-match").then((response) => {
      expect(response.status).to.equal(200);

      expect(response.body.count).to.equal(0);

      expect(response.body.data).to.deep.equal([]);
    });
  });

  it("supports a 100 character search query", () => {
    const query = "a".repeat(100);

    cy.request(`/api/search?q=${query}`).then((response) => {
      expect(response.status).to.equal(200);

      expect(response.body.query).to.equal(query);
    });
  });

  it("supports Unicode search", () => {
    cy.request({
      method: "GET",
      url: "/api/search",
      qs: {
        q: "Café",
      },
    }).then((response) => {
      expect(response.status).to.equal(200);

      expect(response.body.count).to.equal(1);

      expect(response.body.data[0].id).to.equal(4);

      expect(response.body.data[0].name).to.include("Café Élan");
    });
  });
});
