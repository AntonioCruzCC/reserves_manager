(ns reserves-manager.persistance.reserve-balance.schema)

(def schema [{:db/ident :reserve-balance/reserve
              :db/valueType :db.type/ref
              :db/cardinality :db.cardinality/one
              :db/doc "Reference to the reserve"}
             {:db/ident :reserve-balance/balance
              :db/valueType :db.type/double
              :db/cardinality :db.cardinality/one
              :db/doc "The reserves balance"}
             {:db/ident :reserve-balance/date
              :db/valueType :db.type/instant
              :db/cardinality :db.cardinality/one
              :db/doc "The date in which the reserve was set to the value"}])