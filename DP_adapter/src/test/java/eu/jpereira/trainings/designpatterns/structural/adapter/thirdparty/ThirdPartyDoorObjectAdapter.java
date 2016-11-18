package eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty;

import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.CodeMismatchException;
import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.IncorrectDoorCodeException;
import eu.jpereira.trainings.designpatterns.structural.adapter.model.Door;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.ThirdPartyDoor.DoorState;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.ThirdPartyDoor.LockStatus;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeCodeForUnlockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeStateOfLockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotUnlockDoorException;

public class ThirdPartyDoorObjectAdapter implements Door {
	private ThirdPartyDoor tPD = new ThirdPartyDoor();

	@Override
	public void open(String code) throws IncorrectDoorCodeException {
		try {
			this.tPD.unlock(code);
			this.tPD.setState(DoorState.OPEN);
		} catch (CannotUnlockDoorException e) {
			throw new IncorrectDoorCodeException();
		} catch (CannotChangeStateOfLockedDoor e) {
			throw new IncorrectDoorCodeException();
		}

	}

	@Override
	public void close() {
		try {
			this.tPD.setState(DoorState.CLOSED);
		} catch (CannotChangeStateOfLockedDoor e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.tPD.lock();

	}

	@Override
	public boolean isOpen() {
		if(this.tPD.getState() == DoorState.OPEN && this.tPD.getLockStatus() == LockStatus.UNLOCKED)
			return true;
		else
			return false;
	}

	@Override
	public void changeCode(String oldCode, String newCode,
			String newCodeConfirmation) throws IncorrectDoorCodeException,
			CodeMismatchException {
		if(newCode.equals(newCodeConfirmation))
		{
			try {
				this.tPD.unlock(oldCode);
				this.tPD.setNewLockCode(newCode);
			} catch (CannotUnlockDoorException e) {
				throw new IncorrectDoorCodeException();
			} catch (CannotChangeCodeForUnlockedDoor e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			throw new CodeMismatchException();
		}
	}

	@Override
	public boolean testCode(String code) {
		try {
			this.tPD.unlock(code);
		} catch (CannotUnlockDoorException e) {
			return false;
		}
		return true;
	}

}
