async function createNewUser(user) {
    await fetch("/api/admin",
        {method: 'POST',headers: {'Content-Type': 'application/json'}, body: JSON.stringify(user)}).then(response => {
        if (!response.ok) {
            alert(`User with name ${user.name} already exists`);
        }
    });
}

async function addNewUserForm() {
    const newUserForm = document.getElementById("newUser");
    newUserForm.addEventListener('submit', async function (event) {
        event.preventDefault();
        const Name = newUserForm.querySelector('#name')
        console.log(Name)
        console.log(Name.validity)
        console.log(Name.validationMessage)

        const name = newUserForm.querySelector("#name").value.trim();
        const password = newUserForm.querySelector("#password").value.trim();
        const age = newUserForm.querySelector("#age").value.trim();
        const email = newUserForm.querySelector("#email").value.trim();
        // const username = newUserForm.querySelector("#username").value.trim();

        const rolesSelected = document.getElementById("roles");

        let roles = [];
        if (rolesSelected.selectedOptions.length > 0) {
            for (let option of rolesSelected.selectedOptions) {
                if(option.value === ROLE_ADMIN.role) {
                    roles.push(ROLE_ADMIN);
                } else {
                    roles.push(ROLE_USER)
                }
            }
        } else {
            roles.push(ROLE_USER)
        }


        const newUserData = {
            name: name,
            // lastname: lastname,
            age: age,
            password: password,
            email:email,
            roles: roles
        };

        await createNewUser(newUserData)
        newUserForm.reset();

        document.querySelector('a#show-users-table').click();
        await fillTableOfAllUsers();
    });
}