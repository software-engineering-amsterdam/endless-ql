import smtplib
import pytest

@pytest.fixture(scope="module")
def smtp(request):
    smtp = smtplib.SMTP("smtp.gmail.com", 587, timeout=5)
    def fin():
        print ("teardown smtp")
        smtp.close()
    request.addfinalizer(fin)
    return smtp  # provide the fixture value
