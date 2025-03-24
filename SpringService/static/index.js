const addThing = async (event) => {
    event.preventDefault()
    const formData = new FormData(event.target);
    const jsonData = Object.fromEntries(formData.entries())
    console.log('Form JSON', jsonData);
    event.target.reset()
    const response = await fetch('/api/thing', {
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        method: 'PUT',
        body: JSON.stringify(jsonData)
    })
    const responseJson = await response.json()
    console.log('Response', responseJson)
}