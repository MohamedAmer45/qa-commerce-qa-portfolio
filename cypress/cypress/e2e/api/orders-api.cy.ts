type OrderItem = {
  id: number;
  qty: number;
};

type OrderBody = {
  items?: OrderItem[];
  shipping?: {
    email?: string;
    address?: string;
  };
  payment?: {
    cardNumber?: string;
  };
};

describe("Orders API", () => {
  const validShipping = {
    email: "qa.user@example.com",
    address: "123 QA Street",
  };

  const validPayment = {
    cardNumber: "4242424242424242",
  };

  const createOrder = (body: OrderBody, failOnStatusCode = true) => {
    return cy.request({
      method: "POST",
      url: "/api/orders",
      body,
      failOnStatusCode,
    });
  };

  it("creates a valid order", () => {
    createOrder({
      items: [{ id: 10, qty: 2 }],
      shipping: validShipping,
      payment: validPayment,
    }).then((response) => {
      expect(response.status).to.equal(201);

      expect(response.body.data).to.be.an("object");
      expect(response.body.data.id).to.equal("ORD-API-0001");
      expect(response.body.data.status).to.equal("Confirmed");
    });
  });

  it("rejects an empty items array", () => {
    createOrder(
      {
        items: [],
        shipping: validShipping,
        payment: validPayment,
      },
      false,
    ).then((response) => {
      expect(response.status).to.equal(422);
      expect(response.body.error).to.equal("VALIDATION_ERROR");
      expect(response.body.fields.items).to.equal("At least one item required");
    });
  });

  it("rejects a request with missing items", () => {
    createOrder(
      {
        shipping: validShipping,
        payment: validPayment,
      },
      false,
    ).then((response) => {
      expect(response.status).to.equal(422);
      expect(response.body.error).to.equal("VALIDATION_ERROR");
      expect(response.body.fields.items).to.equal("At least one item required");
    });
  });

  it("rejects a request with missing shipping information", () => {
    createOrder(
      {
        items: [{ id: 10, qty: 1 }],
        payment: validPayment,
      },
      false,
    ).then((response) => {
      expect(response.status).to.equal(422);
      expect(response.body.error).to.equal("VALIDATION_ERROR");
      expect(response.body.fields.shipping).to.equal("Valid shipping required");
    });
  });

  it("rejects shipping with missing email", () => {
    createOrder(
      {
        items: [{ id: 10, qty: 1 }],
        shipping: {
          address: "123 QA Street",
        },
        payment: validPayment,
      },
      false,
    ).then((response) => {
      expect(response.status).to.equal(422);
      expect(response.body.error).to.equal("VALIDATION_ERROR");
      expect(response.body.fields.shipping).to.equal("Valid shipping required");
    });
  });

  it("rejects an invalid shipping email", () => {
    createOrder(
      {
        items: [{ id: 10, qty: 1 }],
        shipping: {
          email: "invalid-email",
          address: "123 QA Street",
        },
        payment: validPayment,
      },
      false,
    ).then((response) => {
      expect(response.status).to.equal(422);
      expect(response.body.error).to.equal("VALIDATION_ERROR");
      expect(response.body.fields.shipping).to.equal("Valid shipping required");
    });
  });

  it("rejects shipping with missing address", () => {
    createOrder(
      {
        items: [{ id: 10, qty: 1 }],
        shipping: {
          email: "qa.user@example.com",
        },
        payment: validPayment,
      },
      false,
    ).then((response) => {
      expect(response.status).to.equal(422);
      expect(response.body.error).to.equal("VALIDATION_ERROR");
      expect(response.body.fields.shipping).to.equal("Valid shipping required");
    });
  });

  it("rejects a request with missing payment information", () => {
    createOrder(
      {
        items: [{ id: 10, qty: 1 }],
        shipping: validShipping,
      },
      false,
    ).then((response) => {
      expect(response.status).to.equal(422);
      expect(response.body.error).to.equal("VALIDATION_ERROR");
      expect(response.body.fields.payment).to.equal("Valid test card required");
    });
  });

  it("rejects a card number that fails Luhn validation", () => {
    createOrder(
      {
        items: [{ id: 10, qty: 1 }],
        shipping: validShipping,
        payment: {
          cardNumber: "1234567890123456",
        },
      },
      false,
    ).then((response) => {
      expect(response.status).to.equal(422);
      expect(response.body.error).to.equal("VALIDATION_ERROR");
      expect(response.body.fields.payment).to.equal("Valid test card required");
    });
  });

  it("returns 404 when a product does not exist", () => {
    createOrder(
      {
        items: [{ id: 99999, qty: 1 }],
        shipping: validShipping,
        payment: validPayment,
      },
      false,
    ).then((response) => {
      expect(response.status).to.equal(404);
      expect(response.body.error).to.equal("PRODUCT_NOT_FOUND");
      expect(response.body.productId).to.equal(99999);
    });
  });

  it("rejects a quantity of zero", () => {
    createOrder(
      {
        items: [{ id: 10, qty: 0 }],
        shipping: validShipping,
        payment: validPayment,
      },
      false,
    ).then((response) => {
      expect(response.status).to.equal(422);
      expect(response.body.error).to.equal("INVALID_QUANTITY");
    });
  });

  it("rejects a negative quantity", () => {
    createOrder(
      {
        items: [{ id: 10, qty: -1 }],
        shipping: validShipping,
        payment: validPayment,
      },
      false,
    ).then((response) => {
      expect(response.status).to.equal(422);
      expect(response.body.error).to.equal("INVALID_QUANTITY");
    });
  });

  it("rejects a decimal quantity", () => {
    createOrder(
      {
        items: [{ id: 10, qty: 1.5 }],
        shipping: validShipping,
        payment: validPayment,
      },
      false,
    ).then((response) => {
      expect(response.status).to.equal(422);
      expect(response.body.error).to.equal("INVALID_QUANTITY");
    });
  });

  it("accepts quantity one", () => {
    createOrder({
      items: [{ id: 10, qty: 1 }],
      shipping: validShipping,
      payment: validPayment,
    }).then((response) => {
      expect(response.status).to.equal(201);
      expect(response.body.data.status).to.equal("Confirmed");
    });
  });

  it("rejects a quantity greater than available stock", () => {
    createOrder(
      {
        items: [{ id: 2, qty: 2 }],
        shipping: validShipping,
        payment: validPayment,
      },
      false,
    ).then((response) => {
      expect(response.status).to.equal(409);
      expect(response.body.error).to.equal("INSUFFICIENT_STOCK");
      expect(response.body.productId).to.equal(2);
      expect(response.body.available).to.equal(1);
    });
  });

  it("rejects an out-of-stock product", () => {
    createOrder(
      {
        items: [{ id: 3, qty: 1 }],
        shipping: validShipping,
        payment: validPayment,
      },
      false,
    ).then((response) => {
      expect(response.status).to.equal(409);
      expect(response.body.error).to.equal("INSUFFICIENT_STOCK");
      expect(response.body.productId).to.equal(3);
      expect(response.body.available).to.equal(0);
    });
  });

  it("returns CARD_DECLINED for the decline test card", () => {
    createOrder(
      {
        items: [{ id: 10, qty: 1 }],
        shipping: validShipping,
        payment: {
          cardNumber: "4000000000000002",
        },
      },
      false,
    ).then((response) => {
      expect(response.status).to.equal(402);
      expect(response.body.error).to.equal("CARD_DECLINED");
    });
  });

  it("returns INSUFFICIENT_FUNDS for the insufficient funds card", () => {
    createOrder(
      {
        items: [{ id: 10, qty: 1 }],
        shipping: validShipping,
        payment: {
          cardNumber: "4000000000009995",
        },
      },
      false,
    ).then((response) => {
      expect(response.status).to.equal(402);
      expect(response.body.error).to.equal("INSUFFICIENT_FUNDS");
    });
  });

  it("returns complete successful order details", () => {
    createOrder({
      items: [{ id: 10, qty: 1 }],
      shipping: validShipping,
      payment: validPayment,
    }).then((response) => {
      expect(response.status).to.equal(201);

      expect(response.body.data).to.deep.include({
        id: "ORD-API-0001",
        status: "Confirmed",
        subtotal: 39.9,
        currency: "USD",
      });
    });
  });

  it("calculates subtotal correctly for two speakers", () => {
    createOrder({
      items: [{ id: 10, qty: 2 }],
      shipping: validShipping,
      payment: validPayment,
    }).then((response) => {
      expect(response.status).to.equal(201);
      expect(response.body.data.subtotal).to.equal(79.8);
    });
  });

  it("rounds decimal product prices to two decimal places", () => {
    createOrder({
      items: [{ id: 8, qty: 1 }],
      shipping: validShipping,
      payment: validPayment,
    }).then((response) => {
      expect(response.status).to.equal(201);
      expect(response.body.data.subtotal).to.equal(20);
    });
  });

  it("calculates subtotal for multiple products", () => {
    createOrder({
      items: [
        { id: 10, qty: 2 },
        { id: 11, qty: 1 },
      ],
      shipping: validShipping,
      payment: validPayment,
    }).then((response) => {
      expect(response.status).to.equal(201);
      expect(response.body.data.subtotal).to.equal(168.8);
    });
  });

  it("returns USD as the order currency", () => {
    createOrder({
      items: [{ id: 10, qty: 1 }],
      shipping: validShipping,
      payment: validPayment,
    }).then((response) => {
      expect(response.status).to.equal(201);
      expect(response.body.data.currency).to.equal("USD");
    });
  });

  it("accepts a valid card number containing spaces", () => {
    createOrder({
      items: [{ id: 10, qty: 1 }],
      shipping: validShipping,
      payment: {
        cardNumber: "4242 4242 4242 4242",
      },
    }).then((response) => {
      expect(response.status).to.equal(201);
      expect(response.body.data.status).to.equal("Confirmed");
    });
  });
});
