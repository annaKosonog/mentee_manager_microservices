
print('Start ##########################################################');
db.createUser(
    {
        user: "root",
        pwd: "root",
        roles: [{role:"readWrite", db: "users"}]
    }
);

print('END #################################################################');

